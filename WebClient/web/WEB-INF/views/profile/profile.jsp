<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/includes/header.inc.jsp" />
                <h1>User Profile </h1>
                <div class="profile">
                    <form name="updateprof" method="post" enctype="multipart/form-data" action="ajax/updateUser" >
                        <div class="profiledetail" id="profileContent">
                            <div><div class="rincianLabelProfile"> Name </div>
                                <div id="usernameDisplayDiv" ><c:out value="${user.getUsername()}"/></div><input type="hidden" id="uname" name="uname" value="${user.getUsername()}" />
                                <div id="nameDisplayDiv" name="fullname"><strong id="Fullnama"><c:out value="${user.getFullName()}"/></strong> <a href="#">@<c:out value="${user.getUsername()}"/></a></div>
                                <div id="nameEditDiv"><input type="text" id="fname" name="fname" value="<c:out value="${user.getFullName()}"/>" onkeyup="validateFullName();" onchange="validateFullName();"/></div>
                            </div>	
                            <div>
                                <div class="rincianLabelProfile" > Email </div> 
                                <div id="emailDisplayDiv"><strong id="emailValue"><c:out value="${user.getEmail()}"/></strong></div>
                            </div>

                            <div><div class="rincianLabelProfile">Born date </div>					  
                                <div class="rincianDetailProfile" id="birthDisplayDiv" class="inlineblock" ><strong id="birthDay"><c:out value="${user.getTglLahir()}"/></strong></div>
                                <div class="rincianDetailProfile" id="birthEditDiv" >
                                    <input type="text" id="Bday" name="Bday" value="<c:out value="${user.getTglLahir()}"/>" required="required" placeholder="Birthday (yyyy-mm-dd)" onkeyup="validateBday();" onchange="validateBday();" /><a href="#" onclick="NewCssCal('Bday', 'yyyyMMdd', 'dropdown', false, 24, false, 'past'); return false;"><img src="./images/cal/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                                </div>
                            </div>

                            <div>
                                <div class="rincianLabelProfile" > Major </div> 
                                <div> <strong>Teknik Informatika</strong> </div> 
                            </div>   

                            <div>

                            </div> 
                            <div><div class="rincianLabelProfile" id="oldpassEditLabel"> Old Password </div>
                                  <div id="oldpassEditDetail"><input id="oldpass" type="password" value="" onkeyup="validateOldPassword();" onchange="validateOldPassword();"/></div>
                            </div>
                            <div><div class="rincianLabelProfile" id="passEditLabel"> New Password </div>
                                  <div id="passEditDetail"><input type="password" id="pwd" name="pwd" value="" onkeyup="validatePassword();" onchange="validatePassword();"/></div>
                                  <div id="repassEditDetail"><input type="password" id="pwd_confirm" name="pwd_confirm" value="" onkeyup="validateRePassword();" onchange="validateRePassword();"/></div>
                            </div>
                            <div id="CurPwd"><input type="password" id="Oldpwd" name="pwd" value="<c:out value="${user.getPassword()}"/>"></div>
                            <div>
                                  <div id="changeAvatarLabel" class="rincianLabelProfile">Avatar</div>
                                  <div id="changeAvatarDetail"><input type="file" id="ava" name="ava" accept="image/*"  onkeyup="validateAvatar();" onchange="validateAvatar();" /></div>
                            </div>
                            <%
                                if (request.getAttribute("isUser") == "true"){
                                    out.println("<div id=\"editButton\" class=\"edit_button\"><button type=\"button\" onclick=\"return editProfile()\">Edit</button> </div>");
                                }
                            %>
                            <div id="doneButton" class="done_button"><input type="submit" onclick="return saveProfile();" value="Done" /> </div>			
                        </div>
                    </form>
                    <div class="profilepict"><img src="images/avatars/<c:out value="${user.getAvatar()}"/>" width="200" height="200" alt="Edo Thobi Bram"/></div>
                </div>
				
                <div id="tasklist">

                </div>

<jsp:include page="/WEB-INF/includes/footer.inc.jsp" />