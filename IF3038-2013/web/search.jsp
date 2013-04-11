<%
	String title = "Search Result";
	boolean login_permission = true;
%>
<%@include file="inc/header.jsp" %>
		<script>
			window.onload=function(){localStorage.user_id = <%=user_id%>; 
                            _q = '<%=request.getParameter("q")%>'; _mode = <%=request.getParameter("mode")%>; _page = 0; _done = false;
			tasks = false;
			users = false;
			categories = false;
			getSearchResult(_q,_mode,0);
			window.onscroll = loadMoreSearch;
			}
		</script>
		<div class="content">
			<div class="dashboard">	
				<header>
					<h1>Search Result</h1>
				</header>
				
				<div class="primary">
					<section class="tasks current" id="searchResult">
					
					</section>
				</div>
			
				<div class="secondary">
					<section class="categories">
						<header>
							<h3>Search Tips</h3>
						</header>
						<ul id="categoryList">
							<li>Scroll below to get more data, if any</li>
						</ul>
					</section>
				</div>

			</div>

		</div>
<%@include file="inc/footer.jsp" %>