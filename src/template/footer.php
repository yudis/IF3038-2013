			</div>
			<footer class="site-footer">
			<?php
				$total = count($breadcrumbs);
				foreach ($breadcrumbs as $key => $value)
				{
					echo "<a ";
					foreach ($value as $key2 => $value2)
					{
						echo $key2."=\"".$value2."\" ";
					}
					echo ">".$key."</a>";
					$total--;
					if ($total > 0)
						echo " >> ";
				}
			?>
			</footer>
		</div>
		<script src="js/bajuri.js"></script>
		<!--
		<script src="js/do.js"></script>	
		<script src="js/checker.js"></script>
		-->
	</body>
</html>