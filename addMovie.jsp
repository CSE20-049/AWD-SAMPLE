<jsp:useBean id="administrator" scope="session" type="model.BEANS.Administrator"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Movie</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <style>
    @import url('https://fonts.googleapis.com/css?family=Signika:500');

    * {
      padding: 0;
      margin:  0;
      box-sizing: border-box;
      font-family: "Signika", sans-serif;
    }

    /*Header Styling*/
    header {
      background-color: #18171f;
    }
    nav {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding-top: 7px;
      padding-bottom: 7px;
      padding-left: 10%;
      padding-right: 10%;
    }
    .logo {
      color: #ff0066;
      cursor: pointer;
    }
    span {
      color: #0099cc;
    }
    nav ul li {
      list-style-type: none;
      display: inline-block;
      padding: 10px 20px;
    }
    nav ul li a {
      color: white;
      text-decoration: none;
      font-weight: bold;
    }
    nav ul li a.active {
      color: rgba(16,148,224);
      transition: all 0.2s ease 0s;
      border-bottom: 0.5px solid white;
      border-bottom-width: medium;
    }
    nav ul li a:hover {
      color: #0099cc;
      transition: all 0.2s ease 0s;
      border-bottom: 0.5px solid white;
      border-bottom-width: medium;
    }

    .right-side-nav {
      margin-right: -83px;
    }
    .right-side-nav .Username{
      color: white;
    }
    .right-side-nav .button1 {
      color: white;
      margin-left: 35px;
      padding: 9px 22px;
      background-color: #ff0066;
      border: none;
      border-radius: 60px;
      transition: all 0.1s ease 0s;
      cursor: pointer;
    }
    .right-side-nav .button1:hover {
      background: #990033;
    }
    /*Header Styling*/

    /*Section Styling*/
    section {
      background-color: #101010;
      background-size: cover;
      height: 235vh;
      color: white;
      padding-bottom: 20px;
    }

    .form-popup{
      position: absolute;
      top: 125%;
      left: 50%;
      transform: translate(-50%,-50%);
      width: 700px;
      background: #18171f;
      border-radius: 10px;
    }
    .form-popup h1{
      text-align: center;
      margin-top: 10px;
      color: #ff0066;
      font-size: 30px;
      margin-bottom: 30px;
    }
    .form-popup h3{
      text-align: center;
      padding: 2px 0 15px 0;
      margin-top: -10px;
      margin-bottom: 20px;
      border-bottom: 1px solid #C0C0C0FF;
      color: #ff0066;
    }
    .form-popup form{
      padding: 0 40px;
      box-sizing: border-box;
    }
    form h4{
      color: #0099cc;
      font-size: 20px;
      font-family: "Calibri", sans-serif;
      margin-bottom: 3px;
      font-weight: bold;
    }
    form .text-field{
      position: relative;
      border-bottom: 2px solid #0c658b;
      margin: 20px 0;
    }
    .text-field input{
      /*border-bottom: 2px solid #0c658b;*/
      border: none;
      width: 100%;
      padding: 0 5px;
      height: 40px;
      background: none;
      outline: none;
      color: #f8f8f8;
      font-size: 17px;
    }
    .text-field span::before{
      content: '';
      position: absolute;
      top: 40px;
      left: 0;
      width: 100%;
      height: 2px;
      background: #0c658b;
    }
    .text-field input:focus ~ label, .text-field input:valid ~ label{
      top: -10px;
      color: #0890cc;
    }

    select{
      display: grid;
      color: #d2d2d2;
      text-align: center;
      text-transform: uppercase;
      letter-spacing: 4px;
      font-weight: bold;
      margin-top: 3px;
      margin-bottom: 6px;
      background: transparent;
      padding: 6px 4px;
      border-radius: 4px;

    }
    option{
      color: black;
    }
    .Status-DropDown{
      float: right;
      width: 50%;
    }
    .Genre-DropDown{
      float: left;
      width: 50%;
    }
    input[type="submit"]{
      margin-bottom: 40px;
      border: none;
      margin-top: 34px;
      margin-left: 180px;
      width: 40%;
      height: 39px;
      background: #ff0066;
      font-size: 18px;
      color: #FFFFFF;
      cursor: pointer;
      transition: all 0.1s ease 0s;
    }
    input[type="submit"]:hover{
      background: #990033;
    }

    .message-error{
      color: #22c707;
      margin-top: 0;
      margin-bottom: 1px;
    }
    /*Section Styling*/

    /*Footer Styling*/
    footer {
      position: absolute;
      min-height: 30px;
      width: 100%;
      padding-top: 0;
      color: #FFF;
      border-top: 0.5px solid white;
      border-top-width: thin;
      background-color: #18171f;
    }

    .footer-content {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      text-align: center;
    }

    .socials {
      list-style: none;
      display: flex;
      align-items: center;
      justify-content: center;
      margin: 25px 0 12px 7px;
      margin-bottom: 3px;
      margin-top: 10px;
    }

    .socials li {
      margin: 0 15px;
    }

    .socials a {
      text-decoration: none;
      color: #FFF;
      font-size: 35px;
      letter-spacing: 15px;
    }

    .socials a i {
      transition: all 0.1s ease 0s;
    }

    .socials a:hover  {
      color: #0099cc;
    }

    .footer-bottom {
      color: #666666;
      width:100%;
      padding: 4px 0;
      text-align: center;
      padding-bottom: 15px;
      padding-top: 5px;
    }

    .footer-bottom p {
      font-size: 14px;
      word-spacing: 2px;
      text-transform: capitalize;
      margin-left: 1px;
      padding-bottom: 1px;
      margin-bottom: -7px;
    }
    /*Footer Styling*/


  </style>
</head>
<body>
<header>
  <!-- Navigation Bar contruction -->
  <div class="navigation">
    <nav>
      <h1 class = "logo">R e d<span> F o x</span></h1>
      <ul class = "navigation-links">
        <li><a href = "adminHome.jsp">Home</a></li>
      </ul>
      <div class="right-side-nav">
        <!--requesting from subscriber details from the session object using JSTL Core c:out tag-->
        <label class="Username">${administrator.username}</label>
        <button class = "button1" type="button" onclick="location.href='index.jsp'">LOG OUT</button></a>
      </div>
    </nav>
  </div>
</header>

<section>
  <div class="form-popup" id="signUpForm">
    <h1>ADD <span>MOVIE</span></h1>
    <%
      String message = (String) request.getAttribute("message");
      if (message != null){
    %>
    <p class="message-error" style="text-align: center;" > <%= message%></p>
    <%}%>
    <form action="addMovieServlet" method="post">
      <input hidden type="text" name="@AdminEmailAddress" value="<%= administrator.getEmailAddress()%>">
      <div class="text-field">
        <label>
          <h4>Title</h4>
          <input type="text" required name="@MovieTitle" value="" placeholder="Movie Title">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Year of Production</h4>
          <input type="number" required name="@YearOfProduction" value="" placeholder="Year">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Image</h4>
          <input type="text" required name="@MovieImage" value="" placeholder="Image Link">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Synopsis</h4>
          <input type="text" required name="@MovieSynopsis" value="" placeholder="Synopsis">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Director</h4>
          <input type="text" required name="@Director-Name" value="" placeholder="Name">
          <input type="text" required name="@Director-Surname" value="" placeholder="Surname">
          <input type="text" required name="@Director-DOB" value="" placeholder="DOB">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Actor 1</h4>
          <input type="text" required name="@Actor-1-Name" value="" placeholder="Name">
          <input type="text" required name="@Actor-1-Surname" value="" placeholder="Surname">
          <input type="text" required name="@Actor-1-DOB" value="" placeholder="DOB">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Actor 2</h4>
          <input type="text" required name="@Actor-2-Name" value="" placeholder="Name">
          <input type="text" required name="@Actor-2-Surname" value="" placeholder="Surname">
          <input type="text" required name="@Actor-2-DOB" value="" placeholder="DOB">
        </label>
      </div>
      <div class="text-field">
        <label>
          <h4>Actor 3</h4>
          <input type="text" required name="@Actor-3-Name" value="" placeholder="Name">
          <input type="text" required name="@Actor-3-Surname" value="" placeholder="Surname">
          <input type="text" required name="@Actor-3-DOB" value="" placeholder="DOB">
        </label>
      </div>
      <div class="Genre-DropDown">
        <label>
          <h4>Genre</h4>
          <select name="@MovieGenre" class="Genre">
            <option value="Action">Action</option>
            <option value="Adventure">Adventure</option>
            <option value="Comedy">Comedy</option>
            <option value="Drama">Drama</option>
            <option value="Horror">Horror</option>
          </select>
        </label>
      </div>
      <div class="Status-DropDown">
        <label>
          <h4>Status</h4>
          <select name="@MovieStatus">
            <option value="Available">Available</option>
            <option value="Coming Soon">Coming Soon</option>
            <option value="New">New</option>
          </select>
        </label>
      </div>
      <input type="submit" value="ADD">
    </form>
  </div>
</section>

<footer>
  <div class = "footer-content">
    <ul class = "socials">
      <li><a href = ""><i class = "fa fa-facebook"></i></a></li>
      <li><a href = ""><i class = "fa fa-twitter"></i></a></li>
      <li><a href = ""><i class = "fa fa-instagram"></i></a></li>
    </ul>
  </div>
  <div class = "footer-bottom">
    <p>&copy; RedFox. All rights reserved</p>
  </div>
</footer>
</body>
</html>
