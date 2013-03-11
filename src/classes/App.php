<?php

// Bootstrap
// Load this on the beginning of every page.

class App {
	private $pagesDir = 'pages';
	private $templateDir = 'template';
	private $isPartial = false;
	private $apiEndpoint = 'api';
	private $defaultPage = 'index';

	// bootstrap
	public function bootstrap() {
		error_reporting(E_ALL ^ E_NOTICE);

		$req = $_SERVER['REQUEST_URI'];
		$path = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

		$self = $_SERVER['PHP_SELF'];
		$root = str_replace('\\', '/', dirname($self));
		$selfFile = basename($self);
		$path = substr($path, strlen($root));
		$fragments = explode('/', $path);

		// check if using compatibility mode
		if ($fragments[0] == $this->apiEndpoint) {
			// Load the AJAX API.
			// TODO
		}
		else {
			while ($fragments[0] == '') {
				array_shift($fragments);
			}
			if ($fragments[0] == 'partial') {
				// Don't load the header; used for AJAX page navigation
				$this->isPartial = true;
				$page = count($fragments) > 1 ? $fragments[1] : $this->defaultPage;
			}
			elseif ($fragments[0] == $selfFile) {
				$page = count($fragments) > 1 ? $fragments[1] : $this->defaultPage;
			}
			else {
				$page = $fragments[0] ? $fragments[0] : $this->defaultPage;
			}

			if (preg_match('/^(.*)\.php$/i', $page, $matches)) {
				$page = $matches[1];
			}

			$this->loadPage($page);
		}
	}

	// Load the page specified in $page
	protected function loadPage($page) {
		$file = $this->pagesDir . '/' . $page . '.php';

		if (file_exists($file))
			require_once($file);
		else
			require_once $this->pagesDir . '/404.php';
	}

	// Load the template specified in $tpl
	protected function loadTemplate($tpl) {
		include $this->templateDir . '/' . $tpl . '.php';
	}

	protected function header() {
		if (!$this->isPartial)
			$this->loadTemplate('header');
	}

	protected function footer() {
		$this->loadTemplate('footer');
	}

	protected function calendar() {
		$this->loadTemplate('calendar');
	}
}