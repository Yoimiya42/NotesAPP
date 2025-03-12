// 路由和动作配置
const ACTIONS = {
    LOAD_FORM: { route: '/load/loadForm', method: 'post', fields: [{ name: 'mode', value: 'edit' }, { name: 'id' }] },
    DELETE_NOTE: { route: '/crud/deleteNote', method: 'post', fields: [{ name: 'id' }] },
    PERMANENT_DELETE: { route: '/crud/permanentDelete', method: 'post', fields: [{ name: 'id' }] },
    RECOVER_NOTE: { route: '/crud/recoverNote', method: 'post', fields: [{ name: 'id' }] },
    RENAME_NOTE: { route: '/crud/renameTitle', method: 'post', fields: [{ name: 'id' }, { name: 'title' }] },
    LIST_NOTES: { route: '/load/loadNoteList', method: 'get', fields: [{ name: 'choice' }] },
    SORT_NOTES: { route: '/task/sortNotes', method: 'post', fields: [{ name: 'sortBy' }] }
};

// DOM 加载完成后执行
document.addEventListener('DOMContentLoaded', () => {
    initializeSortSelect();
    highlightCurrentChoice();
    bindActionButtons();
});

// 初始化排序选择
function initializeSortSelect() {
    const sortSelect = document.getElementById('sortSelect');
    const currentSortBy = document.querySelector('meta[name="sortBy"]')?.content || 'modifiedTime_desc';
    sortSelect.value = currentSortBy;
}

// 高亮当前选中的 Choice
function highlightCurrentChoice() {
    const currentChoice = document.querySelector('meta[name="currentChoice"]')?.content || 'all';
    const choiceButtons = document.querySelectorAll('.subject-btn');
    choiceButtons.forEach(button => {
        const buttonChoice = button.getAttribute('data-choice');
        if (currentChoice === buttonChoice || (currentChoice === 'all' && buttonChoice === 'all')) {
            button.classList.add('selected');
        }
    });
}

// 绑定所有动作按钮
function bindActionButtons() {
    const renameButtons = document.querySelectorAll('.rename-btn');
    renameButtons.forEach(button => {
        button.addEventListener('click', () => {
            const noteId = button.getAttribute('data-note-id');
            handleRename(noteId, button);
        });
    });
}

// 处理重命名
function handleRename(noteId, button) {
    const newTitle = prompt('Enter new title:');
    if (newTitle) {
        button.closest('.note-block').querySelector('.note-title').textContent = newTitle;
        fetch(ACTIONS.RENAME_NOTE.route, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${noteId}&title=${newTitle}`
        });
    }
}

// 处理普通删除（移到 Trash）
function handleDelete(event, noteId) {
    event.preventDefault();
    if (confirm('Confirm moving this note to the Trash?')) {
        const noteBlock = document.querySelector(`.note-block[data-note-id="${noteId}"]`);
        if (noteBlock) {
            noteBlock.remove();
        }
        fetch(ACTIONS.DELETE_NOTE.route, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${noteId}`
        });
    }
    return false;
}

// 处理永久删除
function handlePermanentDelete(event, noteId) {
    event.preventDefault();
    if (confirm('Confirm permanently deleting this note?')) { // 添加弹窗提示
        const noteBlock = document.querySelector(`.note-block[data-note-id="${noteId}"]`);
        if (noteBlock) {
            noteBlock.remove();
        }
        fetch(ACTIONS.PERMANENT_DELETE.route, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${noteId}`
        });
    }
    return false;
}

// 处理还原
function handleRecover(event, noteId) {
    event.preventDefault();
    if (confirm('Confirm recovering this note?')) {
        const noteBlock = document.querySelector(`.note-block[data-note-id="${noteId}"]`);
        if (noteBlock) {
            noteBlock.remove(); // 从 Trash 页面移除
        }
        fetch(ACTIONS.RECOVER_NOTE.route, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${noteId}`
        });
    }
    return false;
}