<h1>Role-Based Job Portal Backend</h1>

<p>
A backend system for a job portal supporting <strong>Employers</strong> and
<strong>Job Seekers</strong>, built with a strong focus on
<strong>role-based authorization</strong> and
<strong>service-layer business rule enforcement</strong>.
</p>

<p><strong>Backend Capabilities Demonstrated:</strong></p>
<ul>
  <li>Role-based authorization using Spring Security and JWT</li>
  <li>Service-layer enforcement of business rules</li>
  <li>Pagination and sorting for scalable data access</li>
  <li>Centralized exception handling</li>
  <li>DTO-based REST API design</li>
</ul>

<hr/>

<h2>Overview</h2>
<p>
This project demonstrates how a real-world backend system handles
authentication, authorization, and domain-specific business rules.
All critical decisions are enforced at the service layer to prevent
unauthorized or invalid operations, regardless of API entry point.
</p>

<hr/>

<h2>Key Features</h2>
<ul>
  <li>JWT-based authentication with stateless security</li>
  <li>Role-based access control (Employer, Job Seeker)</li>
  <li>Employers can create, update, and manage job postings</li>
  <li>Job seekers can browse job listings and submit applications</li>
  <li>Pagination and sorting support for job listings</li>
  <li>Strict validation and authorization enforced at the service layer</li>
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
  <li>Thin controllers delegating logic to services</li>
  <li>DTO-based API contracts with validation</li>
  <li>Centralized exception handling for consistent API responses</li>
</ul>

<hr/>

<h2>Backend Design Decisions</h2>
<ul>
  <li>Authentication implemented using stateless JWT tokens</li>
  <li>Authorization enforced at the service layer, not only via annotations</li>
  <li>Business rules validated before persistence to prevent invalid state</li>
  <li>Controllers kept minimal to maintain separation of concerns</li>
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
  <li>Create a PostgreSQL database</li>
  <li>Configure database credentials in <code>application.properties</code></li>
  <li>Build the project using Maven</li>
  <li>Run the Spring Boot application</li>
  <li>Test APIs using Postman or any REST client</li>
</ol>

<hr/>

<h2>What This Project Demonstrates</h2>
<ul>
  <li>Understanding of where backend business logic should reside</li>
  <li>Ability to design secure APIs with role-based constraints</li>
  <li>Handling of real-world backend concerns such as pagination and validation</li>
  <li>Clean separation of concerns and maintainable backend structure</li>
</ul>

<hr/>

<h2>Future Enhancements</h2>
<ul>
  <li>Caching frequently accessed data</li>
  <li>Advanced filtering and search for job listings</li>
</ul>
