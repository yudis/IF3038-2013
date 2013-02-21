// A simple webserver for concatenating templates

// Modules
var connect = require('connect')
  , http = require('http')
  , _fs = require('fs');

// Settings
var FILE_ENCODING = 'utf-8',
    EOL = '\n';

function concat(src) {
    var out = src.map(function(filePath){
            src = _fs.readFileSync(filePath, FILE_ENCODING);
            // src = src.replace('{{ title }}', title);
            return src;
        });

    return out.join(EOL);
}

var app = connect()
  .use(connect.logger('dev'))
  .use(connect.static(__dirname))
  .use(function(req, res){
  	console.log(req.url);
  	var page = req.url.match(/\/([a-zA-Z0-9_\-]+)(\.html)?/)[1];
  	console.log(page);
  	res.end(concat(['src/header.html', 'src/' + page + '.html', 'src/footer.html']));
  })
 .listen(3000);