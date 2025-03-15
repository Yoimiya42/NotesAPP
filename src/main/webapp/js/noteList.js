// Routes Configration
const ACTIONS = {
    DELETE_NOTE: { route: '/crud/deleteNote', method: 'post', fields: [{ name: 'id' }] },
    PERMANENT_DELETE: { route: '/crud/permanentDelete', method: 'post', fields: [{ name: 'id' }] },
    RECOVER_NOTE: { route: '/crud/recoverNote', method: 'post', fields: [{ name: 'id' }] },
    RENAME_NOTE: { route: '/crud/renameTitle', method: 'post', fields: [{ name: 'id' }, { name: 'title' }] },
};


// Move to Trash
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

// Permanently Delete
function handlePermanentDelete(event, noteId) {
    event.preventDefault();
    if (confirm('Confirm permanently deleting this note?')) {
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

function handleRecover(event, noteId) {
    event.preventDefault();
    if (confirm('Confirm recovering this note?')) {
        const noteBlock = document.querySelector(`.note-block[data-note-id="${noteId}"]`);
        if (noteBlock) {
            noteBlock.remove();
        }
        fetch(ACTIONS.RECOVER_NOTE.route, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${noteId}`
        });
    }
    return false;
}