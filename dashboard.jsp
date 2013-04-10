<%@include file="/header.jsp"%>
<%
PreparedStatement pst0 = con.prepareStatement("SELECT DISTINCT `category` FROM `tasks`");
ResultSet rs0 = pst0.executeQuery();
int cats = 0;
while (rs0.next())
{
	cats++;
}
rs0.close();
pst0.close();

int id = Integer.parseInt(session.getAttribute("id"));
%>
			
<div id="main2">
    <div id ="dashboardcontainer">
        <div id ="staticcomponentlist">
            <div class ="letterpanel">
            <a href="#login_form" id="login_pop">Kategori</a>
            </div>
            <!-- popup form #1 jadi ceritanya awalnya gk keliatan -->
            <a href="#x" class="overlay" id="login_form"></a>
            <div class="popup">
				<form name="add_category" method="post" action="category.jsp">
					<h2>Tambah Kategori Tugas</h2>
					<!--<h2>Welcome Guest!</h2>
					<p>Please enter your login and password here</p>-->
					<div>
						<label>Nama Kategori &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: </label>
						<input type ="text" name="category_name"></input>
					</div>
					<div>
						<label for="password">Pengguna Terkait :</label>
						<input type ="text" name="relateduser"></input>
					</div>
					<input type="hidden" name="creator_id" value="<% out.print(id); %>" />
					<input type="submit" name="btn_addCat" value="Ok" />
				</form>

				<a class="close" href="#close"></a>
            </div>
            <%
            PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `categories` ORDER BY id");
            ResultSet rs1 = pst.executeQuery();
            while (rs1.next())
            {
        		boolean found = false;
        		int cat_id = rs1.getInt(1);
        		PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+cat_id);
        		ResultSet rs2 = pst2.executeQuery();
        		while (rs2.next())
        		{
        			int task_id = rs2.getInt(1);
        			PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `assignees` WHERE member="+id+" AND task="+task_id);
        			ResultSet rs3 = pst3.executeQuery();
        			if (rs3.next())
        			{
            %>
            <br /><div onclick='javascript:gettask(<% out.print(session.getAttribute("id")); %>,<% out.print(rs1.getInt(1)); %>);'><a href="#"><% out.print(rs1.getString(2)); %></a></div>
            <%
            			PreparedStatement pst4 = con.prepareStatement("SELECT * FROM editors WHERE member="+id+" AND category="+cat_id);
            			ResultSet rs4 = pst4.executeQuery();
            			if (rst4.next())
            			{
            %>
            <a href ='post.jsp?id=<% out.print(rs1.getInt(1)); %>'><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a>
            <%
            				if (rs2.getInt(3) == id)
            				{
            %>
            <form action="deletecategory.jsp" method="post">
            	<input type="hidden" name="id" value="<% out.print(rs1.getInt(1)); %>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <%
            				}
            				else
            				{
            					out.print("<br />");
            				}
            			}
            			rs4.close();
            			pst4.close();
            			found = true;
            			break;
            		}
            		rs3.close();
            		pst3.close();
            	}
            	if (found == false)
            	{
            		PreparedStatement pst5 = con.prepareStatement("SELECT * FROM `editors` WHERE member="+id+" AND category="+cat_id);
            		ResultSet rs5 = pst5.executeQuery();
            		if (rs5.next())
            		{
            %>
        	<br /><div onclick="javascript:gettask(<?php echo $_SESSION['id'];?>,<?php echo $cat['id'];?>);"><a href="#"><?php echo $cat['name'];?></a></div>
            <a href ="post.php?id=<?php echo $cat['id'];?>"><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a><br />
            <%
            			if (rs2.getInt(3) == id)
            			{
            %>
            <form action="deletecategory.php" method="post">
            	<input type="hidden" name="id" value="<?php echo $cat['id'];?>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <%
            			}
            			else
            			{
            				out.print("<br />");
            			}
            		}
            		rs5.close();
            		pst5.close();
            	}
            	rs2.close();
            	pst2.close();
            }
            rs1.close();
            pst1.close();
            %>
        </div>
        <div id="rincian">
			<%
			PreparedStatement pst6 = con.prepareStatement("SELECT * FROM `categories`");
			ResultSet rs6 = pst6.executeQuery();
			while (rs6.next())
			{
				int idc = rs6.getInt(1);
				PreparedStatement pst7 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+idc);
				ResultSet rs7 = pst7.executeQuery();
				while (rs7.next())
				{
					int task_id = rst7.getInt(1);
					PreparedStatement pst8 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+task_id+" AND member="+id);
					ResultSet rs8 = pst8.executeQuery();
					if (rs8.next())
					{
			%>
			<br /><a href="rinciantugas.php?id=<?php echo $task['id'];?>"><?php echo $task['name']?></a><br />
			Deadline: <strong><?php echo $task['deadline'];?></strong><br />
			<?php
						$res = mysqli_query($con,"SELECT * FROM tags WHERE tagged=$task_id");
						$count_tag = 0;
						while ($tagged = mysqli_fetch_array($res)) {
							$tag[$count_tag] = $tagged['name'];
							$count_tag++;
						}
			?>
			<%
						PreparedStatement pst9 = con.prepareStatement("SELECT * FROM `tags` WHERE tagged="+task_id);
						int count_tag = 0;

			%>
			Tag: <strong>
			<?php
						for ($i = 0; $i < $count_tag; $i++) {
							echo $tag[$i];
							if ($i < $count_tag - 1) echo ",";
						}
			?>
			</strong>
			<br />
			<div id="<?php echo $task_id;?>">
				Status : <strong><?php if ($assignee['finished'] == 1) echo 'Selesai'; else echo 'Belum selesai';?></strong><br />
				<input name="YourChoice" type="checkbox" value="selesai" <?php if($assignee['finished']==1) echo "checked"; ?> onclick="change_status('<?php echo $task_id;?>',<?php echo $assignee['finished'];?>,<?php echo $task_id;?>)"> Selesai
			</div>
			<?php
						if ($task['creator'] == $id) {
			?>
			<form action="deletetask.php" method="post">
				<input type="hidden" name="deltask" value="<?php echo $task_id?>" />
				<input type="submit" name="submit" value="Delete" />
			</form>
			<?php
						}
					}
				}
			}
			?>
            
		</div>
	</div>
	
<?php include 'footer.php';?>
