<html>
    <head>
        <title>Shared To Do List</title>
        <link rel="stylesheet" type="text/css" href="../style.css">
        <link rel="shortcut icon" href="favicon.ico">
        <script type="text/javascript" src="../ajax.js"></script>
        <script type="text/javascript" src="../mpquery.js"></script>
        <script type="text/javascript" src="../validation.js"></script>
        <script type="text/javascript" src="addcatcontroller.js"></script>	
    </head>
    <body onload="checkLogged();">
        <div id="navsearch">
        </div>	
        <div class="clearall container" id="registerform">
            <h2>Add Category</h2>
            <form action="" method="post">
                <fieldset>
                    <p>
                        <label>Category name <abbr title="Required">*</abbr></label>
                        <input value="" id="category"
                               required="required" aria-required="true"					
                               title="Your category name"
                               type="text" spellcheck="false" size="20" onkeyup="getValues('category');" onfocusout="clearSuggestion('categorysuggestion');"/>
                    <div id="categorysuggestion"></div>
                    </p>
                    <p>
                        <label>User <abbr title="Required">*</abbr></label>
                        <input value="" id="users"
                               required="required" aria-required="true"
                               title="Other username"
                               type="text" spellcheck="false" size="20" onkeyup="getValues('users');" onfocusout="clearSuggestion('userssuggestion');"/>
                    <div id="userssuggestion"></div>
                    </p>			
                </fieldset>
                <fieldset>
                    <button id="register" type="submit" onclick="submitNewCategory();
            return false;">Add Category</button>
                </fieldset>
            </form>
        </div>			
    </body>
</html>