			<div id="footer">
				<div id="little">
				</div>
				<div id="siteinfo">
					&copy; 2013. Sukasukague.<br />
					Enjella.Danny.Arya
				</div>
				<div id="botlink">
					<ul>
						<li><a href="dashboard.jsp">DASHBOARD</a></li>
						<li><a href="profil.jsp">PROFIL</a></li>
						<li><a href="logout.jsp">LOGOUT</a></li>
					</ul>
				</div>
			</div>
		</div>
		<%
		if (uri.equals("rinciantugas.jsp"))
		{
			out.print("<script type=\"text/javascript\" src=\"rinciantugas.js\"></script>");
		}
		else if (uri.equals("post.jsp"))
		{
			out.print("<script type=\"text/javascript\" src=\"post.js\"></script>");
		}
		%>
	</body>
</html>
<%
		con.close();
	}
	catch (SQLException ex)
	{
		out.print("Error : " + ex.getMessage());
	}
%>