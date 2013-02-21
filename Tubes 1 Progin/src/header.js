function createHeader()
{
    document.write("\
        <!--header-->\n\
        <div class=\"header inline\">\n\
            <div class=\"container\">\n\
                <div class=\"inline\" name=\"logo\">\n\
                    <div class=\"logo\">\n\
                        <a href=\"../dashboard/dashboardUI.html\">\n\
                            <img src=\"../../images/logo.png\" alt=\"Home Page\"/>\n\
                        </a>\n\
                    </div>\n\
                </div>\n\
                <div class=\"newline\">\n\
                </div>\n\
                <div class=\"inline\" name=\"home\">\n\
                    <div class=\"button\">\n\
                        <a href=\"../dashboard/dashboardUI.html\">Home</a>\n\
                    </div>\n\
                </div>\n\
                <div class=\"inline\" name=\"profile\">\n\
                    <div class=\"button\">\n\
                        <a href=\"../profile/profileUI.html\">Profile</a>\n\
                    </div>\n\
                </div>\n\
                <div class=\"hidden\" name=\"login\">\n\
                    <div class=\"button\" onclick=\"doLogin()\">\n\
                        Login\n\
                    </div>\n\
                </div>\n\
                <div class=\"inline\" name=\"logout\">\n\
                    <div class=\"button\" onclick=\"doLogout()\">\n\
                        Logout\n\
                    </div>\n\
                </div>\n\
                <div class=\"inline\" name=\"search\">\n\
                    <div class=\"form search\">\n\
                        <span class=\"row\">\n\
                            <input name=\"searchtextbox\" type=\"search\" value=\"search\" />\n\
                            <span class=\"button\" onclick=\"doSearch()\">\n\
                                Search\n\
                            </span>\n\
                        </span>\n\
                    </div>\n\
                </div>\n\
            </div>\n\
        </div>");
}

function doLogout()
{
    var logout = $("name", "logout")[0];
    var login = $("name", "login")[0];

    changeClassName(logout, "hidden");
    changeClassName(login, "inline");
}

function doLogin()
{
    var login = $("name", "login")[0];
    var logout = $("name", "logout")[0];
    
    changeClassName(login, "hidden");
    changeClassName(logout, "inline");
}

function doSearch()
{
    alert($("name", "searchtextbox")[0].value);    
}

createHeader();