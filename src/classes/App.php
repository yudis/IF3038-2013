<?php

// Bootstrap
// Load this on the beginning of every page.

class App {
	private $pagesDir = 'pages';
	private $templateDir = 'template';
	private $apiEndpoint = 'api';
	private $defaultPage = 'index';
	private $isPartial = false;

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

		// Start session
		$this->startSession();

		// Check whether loading REST API or a page
		if ($fragments[0] == $this->apiEndpoint) {
			// Load the REST API.
			require_once 'RestApi.php';

			$restApi = new RestApi($this);
			if ($_SERVER['REQUEST_METHOD'] == 'POST')
				$restApi->params = $_POST;
			else
				$restApi->params = $_GET;

			$method = $fragments[1];

			$success = true;
			if (method_exists($restApi, $method)) {
				// Method exists
				// TODO wrap this in try..catch (if tubes spec allows)
				try {
					$output = call_user_func(array($restApi, $method), $restApi->params);
				}
				catch (Exception $e) {
					$success = false;
					$output = array(
						'error_type' => 'exception',
						'message' => $e->getMessage()
					);
				}
			}
			else {
				$success = false;
				$output = array(
					'error_type' => 'not_found',
					'message' => 'The REST API method ' . $method . ' does not exist.'
				);
			}

			header($success ? 'HTTP/1.1 200 OK' : 'HTTP/1.1 500 Internal Server Error');
			header('Content-type: application/json');
			echo json_encode($output);
		}
		else {
			while ($fragments[0] == '' && count($fragments) > 1) {
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

	public function startSession() {
		session_start();
	}

	public function destroySession() {
		session_destroy();
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