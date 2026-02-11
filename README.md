<h1>Role-Based Job Portal Backend</h1>

<p><strong>Production-focused backend system built with Java & Spring Boot.</strong><br>
Demonstrates real-world backend concerns: authentication, authorization, business rules, testing, and CI.</p>

<hr>

<h2>Overview</h2>

<p>This project is a <strong>role-based Job Portal backend</strong> designed to reflect how backend services are built and secured in real systems.</p>

<p>The focus is not UI, but:</p>

<ul>
  <li>enforcing business rules centrally</li>
  <li>securing APIs correctly</li>
  <li>keeping the system safe even if controllers change</li>
</ul>

<p><strong>Supported roles:</strong> Employer, Job Seeker</p>

<hr>

<h2>Core Backend Responsibilities Covered</h2>

<ul>
  <li>REST API design</li>
  <li>Stateless authentication using JWT</li>
  <li>Role-based authorization</li>
  <li>Service-layer business rule enforcement</li>
  <li>Layered architecture (Controller → Service → Repository)</li>
  <li>PostgreSQL persistence using JPA</li>
  <li>Unit testing of critical logic</li>
  <li>Continuous Integration (CI)</li>
</ul>

<hr>

<h2>Tech Stack</h2>

<ul>
  <li><strong>Language:</strong> Java 17</li>
  <li><strong>Framework:</strong> Spring Boot, Spring MVC, Spring Security</li>
  <li><strong>Authentication:</strong> JWT (stateless)</li>
  <li><strong>Database:</strong> PostgreSQL, Hibernate / JPA</li>
  <li><strong>Testing:</strong> JUnit 5, Mockito</li>
  <li><strong>Build Tool:</strong> Maven</li>
  <li><strong>CI:</strong> GitHub Actions (Maven build, Java 17)</li>
  <li><strong>Deployment:</strong> AWS EC2 (environment-based configuration)</li>
</ul>

<hr>

<h2>Architecture</h2>

<pre>
controller/   -> REST endpoints
service/      -> Business logic & authorization rules
repository/   -> Data access (JPA)
dto/          -> API contracts
security/     -> JWT filters & security configuration
exception/    -> Global exception handling
config/       -> Application configuration
</pre>

<p><strong>Key principle:</strong><br>
Authorization and validation are enforced in the <strong>service layer</strong>, not only at the controller level.</p>

<hr>

<h2>Functional Capabilities</h2>

<h3>Job Seeker</h3>
<ul>
  <li>Register & login</li>
  <li>Apply to job postings</li>
  <li>View application history</li>
</ul>

<h3>Employer</h3>
<ul>
  <li>Create, update, and delete job postings</li>
  <li>View applicants</li>
  <li>Accept or reject applications</li>
</ul>

<h3>Public</h3>
<ul>
  <li>Browse job listings</li>
</ul>

<hr>

<h2>Security Model</h2>

<ul>
  <li>JWT-based stateless authentication</li>
  <li>Role-based authorization (Employer vs Job Seeker)</li>
  <li>Ownership checks for employer-managed job posts</li>
  <li>Protection against unauthorized actions and invalid state changes</li>
</ul>

<p>The system remains secure even if endpoints are misused.</p>

<hr>

<h2>Testing</h2>

<p>Unit tests are written for <strong>critical backend logic</strong>, including:</p>

<ul>
  <li>authorization rules</li>
  <li>prevention of duplicate job applications</li>
  <li>job ownership validation</li>
  <li>invalid operations and error cases</li>
</ul>

<p>Testing focuses on <strong>service-layer behavior</strong>, not controller wiring.</p>

<hr>

<h2>CI / Build</h2>

<ul>
  <li>GitHub Actions CI pipeline</li>
  <li>Automatically builds the project on every push</li>
  <li>Uses Java 17 and Maven</li>
  <li>Ensures the backend compiles before merge</li>
</ul>

<hr>

<h2>How to Run Locally</h2>

<ol>
  <li>Clone the repository</li>
  <li>Configure PostgreSQL credentials in <code>application.yml</code></li>
  <li>Run the application:
    <pre>mvn spring-boot:run</pre>
  </li>
  <li>Test APIs using Postman or any REST client</li>
  <li>Authenticate to access protected endpoints (JWT required)</li>
</ol>

<hr>

<h2>Deployment</h2>

<ul>
  <li>AWS EC2</li>
  <li>Any JVM-compatible cloud environment</li>
  <li>Docker-supported platforms</li>
</ul>

<hr>

<h2>Developer</h2>

<p>
<strong>Deepsan Bhandari</strong><br>
Backend Java Developer (Spring Boot)<br>
Email: deepsanbhandari7@gmail.com<br>
GitHub: <a href="https://github.com/DeepsanBhandari">github.com/DeepsanBhandari</a><br>
LinkedIn: <a href="https://www.linkedin.com/in/deepsan7/">linkedin.com/in/deepsan7</a>
</p>

<hr>

<h2>Purpose of This Repository</h2>

<p>This project exists to demonstrate:</p>

<ul>
  <li>backend engineering fundamentals</li>
  <li>secure API design</li>
  <li>disciplined architecture</li>
  <li>readiness to contribute to real backend codebases</li>
</ul>
