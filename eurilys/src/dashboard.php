<?php include 'logged_in_header.php'; ?>	

<section>
	<!-- Navigation Bar -->
	<?php include 'navigation_bar.php'; ?>
	
	<div id="dynamic_content">
		<script> generateTask('all');  </script>				
	</div>
</section>
		
<?php include '../footer.php'; ?>