<!DOCTYPE html>
<html> 
    <head>
        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
		<link rel="stylesheet" type="text/css" href="./styles/profile.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
		<script src="scripts/ajaxhelper.js" type="application/javascript"></script>
		<script src="scripts/profile.js" type="application/javascript"></script>
        <title>Todolist: User Profile</title>
    </head> 
    <body>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.html">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="index.html">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="#">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
                                <input type="image" src="images/search.png" name="sumbit" class="searchbox_submit" alt="search..."/>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="content">
                <h1>User Profile</h1>
                <div class="profile">
                    <div class="profiledetail" id="profileContent">

                    </div>
                    <div class="profilepict"><img src="images/avatar.png" width="200" height="200" alt="Edo Thobi Bram"/></div>
                </div>
                <h1> Activity </h1>
                <h2>Kategori 1</h2>
                <div class="tugas">
                    <div><a href="#">Tugas Pertama</a></div>
                         <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
                         <div>
                             Tags: 
                             <ul class="tag">
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                              </ul>
                         </div>
                </div>
                <div class="tugas">
                     <div><a href="#">Tugas Pertama</a></div>
                         <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
                         <div>
                             Tags: 
                             <ul class="tag">
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                              </ul>
                         </div>
               </div>
               
               <h2>Kategori 4</h2>
                <div class="tugas">
                    <div><a href="#">Tugas Pertama</a></div>
                         <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
                         <div>
                             Tags: 
                             <ul class="tag">
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                              </ul>
                         </div>
                </div>
                <div class="tugas">
                     <div><a href="#">Tugas Pertama</a></div>
                         <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
                         <div>
                             Tags: 
                             <ul class="tag">
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                                 <li>Tag</li>
                              </ul>
                         </div>
               </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body> 
</html>
