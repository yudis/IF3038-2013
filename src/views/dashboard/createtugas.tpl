<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title>Todolist: Create Tugas</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
        <script src="scripts/formtugas.js" type="application/javascript"></script>
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
            <div class ="content">
                <h1>Buat Tugas Baru</h1>
                <div class="formtugas">
                    <form onsubmit="return validate (this);" name="formTugas1"  method="post" enctype="multipart/form-data" action="createtugas.php">
                        <ul class="item">
                            <li id="folil1">
                                <label id="title1">Nama task:</label>
                                <div>
                                    <input id="namatask" name="namatask" type="text" maxlength="25" tabindex="1" pattern="[A-Za-z0-9 ]{1,25}"
                                           title="Nama task tidak diperbolehkan menggunakan karakter spesial"/>
                                </div>
                            </li>

                            <li id="folil2">
                                <label id="title2">Attachment:</label>
                                <div>
                                    <input id="attachment" name="attachment" type="file" tabindex="2" accept="application/pdf,application/msword,image/*"/>
                                </div>
                            </li>

                            <li id="folil3">
                                <label id="title3">Deadline:</label>
                                <div>
                                    <input id="deadline" name="deadline" type="date" tabindex="3"/>
                                </div>
                            </li>

                            <li id="folil4">
                                <label id="title4">Assignee:</label>
                                <div>
                                    <input id="assignee" name="assignee" type="text" tabindex="4" pattern="[A-Za-z0-9 ]{1,}"  list="user" />
                                    <datalist id="user">
                                        <option value="Abraham Krisnanda Santoso">
                                        <option value="Edward Samuel Pasaribu">
                                        <option value="Stefanus Thobi Sinaga">
                                    </datalist>
                                </div>
                            </li>

                            <li id="folil5">
                                <label id="title5">Tag:</label>
                                <div>
                                    <input id="tag" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9 ]{1,}"/>
                                </div>
                            </li>

                            <li id="btn">
                                <input type="submit" value="Submit" class="button"/>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body>
</html>