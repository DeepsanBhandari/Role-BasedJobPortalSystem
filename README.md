<h1 align="center"> Role-Based Job Portal System (Backend)</h1>
<p align="center"><em>A production-ready Java + Spring Boot backend showcasing clean architecture, security, and real-world engineering.</em></p>

<hr>

<h2>Project Overview</h2>
<p>
This is a <strong>role-based Job Portal Backend</strong> built with <strong>Java, Spring Boot, Spring Security, JWT, and PostgreSQL</strong>. 
It demonstrates skills that employers expect from a <strong>junior backend developer</strong>:
</p>

<ul>
  <li>REST API development</li>
  <li>Role-based access control (Employer / Job Seeker)</li>
  <li>JWT Authentication</li>
  <li>Service-layer business logic</li>
  <li>Layered architecture (Controller → Service → Repository)</li>
  <li>Database design using PostgreSQL + JPA</li>
  <li>Clean code, validation, and exception handling</li>
  <li>Unit testing using JUnit & Mockito</li>
</ul>

<hr>

<h2> Key Features</h2>
<ul>
  <li><strong>✔ Real Authentication</strong> – Login, JWT tokens, password hashing</li>
  <li><strong>✔ Real Authorization</strong> – Employers vs Job Seekers security checks</li>
  <li><strong>✔ Clean Architecture</strong> – Easy to maintain and extend</li>
  <li><strong>✔ Centralized Business Logic</strong> – Secure even if APIs change</li>
  <li><strong>✔ Error Handling</strong> – Global exception handling</li>
  <li><strong>✔ Cloud Ready</strong> – Deployable to AWS or Docker</li>
</ul>

<hr>

<h2>🛠️ Tech Stack</h2>
<table>
  <tr><td><strong>Language</strong></td><td>Java 17</td></tr>
  <tr><td><strong>Frameworks</strong></td><td>Spring Boot, Spring MVC, Spring Security</td></tr>
  <tr><td><strong>Authentication</strong></td><td>JWT (Stateless)</td></tr>
  <tr><td><strong>Database</strong></td><td>PostgreSQL + Hibernate / JPA</td></tr>
  <tr><td><strong>Testing</strong></td><td>JUnit 5, Mockito</td></tr>
  <tr><td><strong>Tools</strong></td><td>Maven, Git, Postman</td></tr>
  <tr><td><strong>Deployment</strong></td><td>AWS EC2</td></tr>
</table>

<hr>

<h2>Project Structure</h2>
<pre>
src/
 ├── controller/         # Routes & REST endpoints
 ├── service/            # Business logic & authorization rules
 ├── repository/         # Database access
 ├── dto/                # Data Transfer Objects
 ├── security/           # JWT, filters, authentication providers
 ├── exception/          # Global exception handler
 └── config/             # Application configuration
</pre>

<hr>

<h2> API Capabilities</h2>

<h3> Job Seeker</h3>
<ul>
  <li>Register & Login</li>
  <li>Apply to Jobs</li>
  <li>View Applied History</li>
</ul>

<h3> Employer</h3>
<ul>
  <li>Create, Update, Delete Job Posts</li>
  <li>View Applicants</li>
  <li>Accept/Reject Applications</li>
</ul>

<h3> Public</h3>
<ul>
  <li>Browse all Jobs</li>
</ul>

<hr>

<h2> Security</h2>
<ul>
  <li>JWT Authentication</li>
  <li>Role-based authorization</li>
  <li>Ownership checks for employers</li>
  <li>Service-layer protection (secure even if endpoints change)</li>
  <li>Prevents unauthorized job posting / application access</li>
</ul>

<hr>

<h2> Unit Testing</h2>
<p>Using JUnit + Mockito:</p>
<ul>
  <li>Authorization logic</li>
  <li>Application rules (no duplicate applications)</li>
  <li>Job ownership checks</li>
  <li>Error cases and invalid operations</li>
</ul>

<hr>

<h2>🚀 Deployment</h2>
<p>This backend is deployable on cloud platforms:</p>
<ul>
  <li>AWS EC2</li>
  <li>Render / Railway</li>
  <li>Docker supported</li>
</ul>

<hr>

<h2>👨‍💻 Developer</h2>
<p>
<strong>Deepsan Bhandari</strong><br>
Backend Developer (Java & Spring Boot)<br>
Email: <a href="mailto:deepsanbhandari7@gmail.com">deepsanbhandari7@gmail.com</a><br>
GitHub: <a href="https://github.com/DeepsanBhandari">github.com/DeepsanBhandari</a><br>
LinkedIn: <a href="https://www.linkedin.com/in/deepsan7/">linkedin.com/in/deepsan7</a>
</p>

<hr>

<h2>⭐ Why This Project Helps Me Stand Out as a Junior Developer</h2>
<ul>
  <li>Shows real backend engineering, not just simple CRUD</li>
  <li>Demonstrates real-world problems: roles, applications, security</li>
  <li>Proves I understand authentication, validation & architecture</li>
  <li>Shows readiness for professional backend tasks</li>
</ul>

<p><strong>Thank you for reviewing this project!</strong></p>
