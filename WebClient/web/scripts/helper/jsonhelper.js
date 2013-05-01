function json_parse(text) {
    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
    
    // Memperbaiki unicode character
    text = String(text);
    cx.lastIndex = 0;
    if (cx.test(text)) {
        text = text.replace(cx, function(a) {
            return '\\u' +
                    ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        });
    }

    // Menghapus karakter tertentu
    if (/^[\],:{}\s]*$/
            .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
            .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
            .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
        
        return eval('(' + text + ')');
    }
    
    // If the text is not JSON parseable, then a SyntaxError is thrown.
    throw new SyntaxError('json_parse');
}
