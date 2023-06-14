<html>
<style>
body, html {
	height: 100%;
	margin: 0;
}

.bgimg {
	background-image: url('/university.jpg');
	height: 100%;
	background-position: center;
	background-size: cover;
	position: relative;
	color: black;
	font-family: "Courier New", Courier, monospace;
	font-size: 25px;
}

.middle {
	position: absolute;
	top: 20%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
}

hr {
	color: black;
	margin: auto;
	width: 40%;
}
</style>
<body>
<head>
<title>Welcome University</title>
</head>

<div class="bgimg">
	<div class="middle">
		<h1>Welcome University</h1>
		<hr>
		<button type="button">Login</button>
	</div>
</div>
</body>
</html>