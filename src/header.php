<nav>
    <ul><div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    
    <li><div id = "dashboardlink"></div></li><li><div id="profil"></div></li><li><div><a href="index.php" onclick="signout()">Logout</a></div></li>
    </ul>
    <div class="search">
                        <div id="searchwrapper">
                            <form action="search2.php" method="get">
                                <input type="text" id="search" class="searchbox" name="searchword_header" value="" placeholder="Search.." />
                                <button type="button"  name="sumbit" class="searchbox_submit" alt="search..." onClick="SearchmodeHeader()"/><img src="images/search.png" class="search_image"height="30" width="30" ></button>
                                <button type="button" name="filter" class="filter_submit" onClick="showfilterbox()"><img src = "images/filterarrow.png" class="filter_image"></button>
                            </form>
                            
                        </div>
                        
                        <div id = "f_menu">
                        <form>
                            	<div id= "menuleft">
                                	<div id = "mainfilter"><input type = "radio" name="filter" value="all" id = "al" checked>All</input></div>
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="username" id = "user">Username</input></div>
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="category" id="categ">Judul Kategori</input></div>
                                </div>
                                <div id = "verticalline"></div>
                                <div id = "menuright">
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="judultask" id="taskname">Judul Task</input></div>
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="tag" id="tag">Tag</input></div>
                                </div>
                         </form>
                        </div>
                           
                    </div>
</nav>