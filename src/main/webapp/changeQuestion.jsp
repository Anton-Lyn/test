<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change question</title>
</head>
<body>
<h1>Change question in test</h1>

<form action="takeQuestion" method="post">
  <p>
    <label>
      Enter your question.
    </label>
    <input type="text" placeholder="Question" name="question" required>
  </p>
  <p>
    <label>
      Enter first answer.
    </label>
    <input type="text" placeholder="Question" name="answer1" required>
  </p>
  <p>
    <label>
      Enter second answer.
    </label>
    <input type="text" placeholder="Question" name="answer2" required>
  </p>
  <p>
    <label>
      Enter third answer.
    </label>
    <input type="text" placeholder="Question" name="answer3" required>
  </p>
  <p>
    <label>
      Enter true answer.
    </label>
    <input type="text" placeholder="Question" name="trueAnswer" required>
  </p>
  <button type="submit">Change question</button>
</form>

<form action="workWithTest.jsp">
  <p><input type="submit" value="Edit test"></p>
</form>

</body>
</html>
