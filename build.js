// settings
var FILE_ENCODING = 'utf-8',
    EOL = '\n';
 
// setup
var _fs = require('fs');
 
function concat(opts) {
    var fileList = opts.src;
    var distPath = opts.dest;
    var title = opts.title;
    var out = fileList.map(function(filePath){
            src = _fs.readFileSync(filePath, FILE_ENCODING);
            src = src.replace('{{ title }}', title);
            return src;
        });

    _fs.writeFileSync(distPath, out.join(EOL), FILE_ENCODING);
    console.log(' '+ distPath +' built.');
}

var files = ['index', 'new_tugas', 'profile', 'dashboard'];
var titles = ['Home', 'New Task', 'Profile', 'Dashboard'];

for (i = 1; i <= 8; i++) {
    files.push('view_tugas_' + i);
    titles.push('Task Details');
}

files.forEach(function(v, k) {
    var title = titles[k];
    concat({
        src : [
            'src/header.html',
            'src/' + v + '.html',
            'src/footer.html'
        ],
        dest : 'build/' + v + '.html',
        title : title
    });
});