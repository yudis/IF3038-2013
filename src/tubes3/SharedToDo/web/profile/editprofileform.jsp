<%
    String username = request.getParameter("u").trim();
    String e = request.getParameter("e").trim();
    String fullname = request.getParameter("fullname").trim();
    String email = request.getParameter("email").trim();
    String dob = request.getParameter("dob").trim();
    String temp1 = request.getParameter("photo").trim();
    String temp2 = request.getParameter("p").trim();
%>
<html>
    <head>
        <title>Shared To Do List</title>
        <link rel="stylesheet" type="text/css" href="../style.css">
        <link rel="shortcut icon" href="favicon.ico">
        <script type="text/javascript" src="../ajax.js"></script>
        <script type="text/javascript" src="../mpquery.js"></script>
        <script type="text/javascript" src="profilecontroller.js"></script>	
        <script type="text/javascript" src="../validation.js"></script>	
    </head>
    <body onload="checkLogged();">
        <div id="navsearch">
        </div>		
        <div class="clearall container" id="registerform">
            <h2>Edit Profile</h2>
            <form action="profilesubmit.jsp?u=<%=username%>&e=1" method="post" enctype="multipart/form-data" onsubmit="submitChange(); alert('Successfully editing the profile!');">
                <fieldset>
                    <p>
                        <label>Full name <abbr title="Required">*</abbr></label>
                        <input value='<%=fullname%>' id="fullname" name="fullname"
                               required="required" aria-required="true"
                               pattern="^[a-zA-Z]+ [a-zA-Z]+$"
                               title="Your full name (first and last name)"
                               type="text" spellcheck="false" size="20" onkeyup="checkEdited(this);" onchange="setEdited(0);" onfocusout="checkEdited(this);" />
                    </p>
                    <p>
                        <label>Email <abbr title="Required">*</abbr></label>
                        <input name="email" id="email" value="<%=email%>"
                               required="required" aria-required="true"
                               pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
                               title="Your email address"
                               type="email" spellcheck="false" size="20" onkeyup="checkEdited(this);" onchange="setEdited(1);" onfocusout="checkEdited(this);" />
                    </p>
                    <p>
                        <label>Password <abbr title="Required">*</abbr></label>
                        <input value="<%=temp2%>" id="password" name="password" onchange="checkPassword(); setEdited(2)"
                               required="required" aria-required="true"
                               pattern="^[- \w\d\u00c0-\u024f]{8,}$"
                               title="Your password"
                               type="password" spellcheck="false" size="20" onkeyup="checkEdited(this);" onfocusout="checkEdited(this);" />
                    </p>
                    <p>
                        <label>Confirm password <abbr title="Required">*</abbr></label>
                        <input value="<%=temp2%>"  id="confirmpassword" name="confirmpassword" onchange="checkConfirmPassword()"
                               required="required" aria-required="true"
                               pattern="^[- \w\d\u00c0-\u024f]{8,}$"
                               title="Your password (same as above)"
                               type="password" spellcheck="false" size="20" />
                    </p>			
                    <p>
                        <label>Date of birth <abbr title="Required">*</abbr></label>
                        <input value="<%=dob%>"  id="dob" name="dob" onchange="checkDOB(); setEdited(3);"
                               required="required" aria-required="true"
                               pattern="^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$"
                               title="Your date of birth"
                               type="date" spellcheck="false" size="20" onfocusout="checkEdited(this);" />
                    </p>
                    <!--output id="list"></output-->
                    <p>
                    <div class='box' id="avatar">
                        <img id='avatar2' src='<%="../images/" + temp1%>' width='200' height='200'>
                    </div>
                    </p>
                    <p>
                        <input type="file" id="filename" name="file" value='<%=temp1%>' onchange="CheckFiles(); upload(this);" />		
                    </p>				
                </fieldset>
                <fieldset>
                    <button id="edit" type="submit">Submit</button>
                </fieldset>
            </form>
        </div>		
    </body>
</html>