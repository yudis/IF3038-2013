
<footer>
	<div id="footer_wrap" class="wrap">
		<div id="footer_left">
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
		</div>
		<div id="footer_right">
			Copyright by ImbAlAncE.
		</div>
	</div>
</footer>