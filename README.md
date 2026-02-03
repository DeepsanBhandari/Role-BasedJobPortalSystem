<h1>Role-Based Job Portal Backend</h1>

<p>
A backend system for a job portal supporting <strong>Employers</strong> and
<strong>Job Seekers</strong>, built with a strong focus on
<strong>role-based authorization</strong> and
<strong>service-layer business rule enforcement</strong>.
</p>

<hr/>

<h2>Overview</h2>
<p>
This project demonstrates how a real-world backend system handles
authentication, authorization, and domain-specific business rules.
All critical decisions are enforced at the service layer to prevent
unauthorized or invalid operations.
</p>

<hr/>

<h2>Key Features</h2>
<ul>
  <li>JWT-based authentication with stateless security</li>
  <li>Role-based access control (Employer, Job Seeker)</li>
  <li>Employers can create and manage job postings</li>
  <li>Job seekers can browse jobs and submit applications</li>
  <li>Strict authorization and validation enforced at the service layer</li>
</ul>

<hr/>

<h2>Technology Stack</h2>
<ul>
  <li><strong>Language:</strong> Java</li>
  <li><strong>Framework:</strong> Spring Boot, Spring MVC</li>
  <li><strong>Security:</strong> Spring Security, JWT</li>
  <li><strong>Database:</strong> PostgreSQL</li>
  <li><strong>Persistence:</strong> Spring Data JPA</li>
  <li><strong>Build Tool:</strong> Maven</li>
</ul>

<hr/>

<h2>Architecture & Design</h2>
<ul>
  <li>Layered architecture (Controller, Service, Repository)</li>
  <li>DTO-based API contracts with validation</li>
  <li>Centralized exception handling for consistent API responses</li>
  <li>Business rules enforced independently of API entry points</li>
</ul>

<hr/>

<h2>Backend Design Decisions</h2>
<ul>
  <li>Authentication is stateless using JWT tokens</li>
  <li>Authorization is enforced at the service layer, not only through annotations</li>
  <li>Controllers remain thin, delegating logic to services</li>
  <li>Invalid or unauthorized actions are blocked before persistence</li>
</ul>

<hr/>

<h2>Testing</h2>
<ul>
  <li>Unit tests written for critical service-layer business logic</li>
  <li>Authorization and validation rules verified using JUnit 5</li>
</ul>

<hr/>

<h2>Running the Application Locally</h2>
<ol>
  <li>Clone the repository</li>
  <li>Configure PostgreSQL database credentials</li>
  <li>Build the project using Maven</li>
  <li>Run the Spring Boot application</li>
  <li>Test APIs using Postman or any REST client</li>
</ol>

<hr/>

<h2>Future Enhancements</h2>
<ul>
  <li>Pagination and sorting for job listings</li>
  <li>Refresh token implementation</li>
  <li>Caching frequently accessed data</li>
</ul>
