/**bj
 * @author gmochid
 */

(function($) {
	$.User = function(username, password, email, fullname, birthdate, avatar) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.birthdate = birthdate;
		this.avatar = avatar;
	}
	
	$.Users = {
		serialize: function (users) {
			return JSON.stringify(users);
		},
		
		deserialize: function (jsonstring) {
			if ((jsonstring === '') && (jsonstring === undefined)) {
				return Array();
			}
			
			var users = [];
			var objs = JSON.parse(jsonstring);
			
			for (var i=0; i < objs.length; i++) {
				users.push( new User (
					objs[i].username, 
					objs[i].password, 
					objs[i].email,       
					objs[i].fullname, 
					objs[i].birthdate,
					objs[i].avatar     
				))
			}
			
			return users;
		},
		
		load: function () {
			return $.Users.deserialize(window.localStorage['Users']);
		},
		
		save: function(users) {
			window.localStorage['Users'] = $.Users.serialize(users);
		},
		
		clear: function() {
			window.localStorage.removeItem('Users');
			return Array();
		},
		
		add: function(user) {
			var users = this.load();
			users.push(user);
			this.save(users);
		}
	}
	
	$.Session = {
		login: function(username, password) {
			var users = $.Users.load();
			
			var user = undefined;
			for (var i=0; i < users.length; i++) {
				if (users[i].username === username) {
					user = users[i];
					break;
				}
			}
			
			if (user == undefined) {
				console.log("ERROR: username not found");
				return false;
			}
			
			if (password == user.password) {
				window.localStorage['login'] = 'true';
				window.localStorage['login_user'] = JSON.stringify(user);
				
				console.log("Login Success for " + user.username);
				return true;
			}
			
			console.log("ERROR: incorrect password");
			return false;
		}
	}
	
	if ($.Users.load().length == 0) {
		console.log(0);
		$.Users.add(new User(
			"sonny",
			"fabrianoktavino",
			"unyu@aneh.com",
			"Agatha Christie",
			"1990-02-03",
			"tes.jpg"
		));
	};
})(window)
