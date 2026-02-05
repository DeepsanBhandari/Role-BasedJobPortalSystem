<div align="center">

<h1> Role-Based Job Portal Backend (Spring Boot)</h1>

<p>
A backend-focused Spring Boot project demonstrating
<strong>service-layer authorization</strong>,
<strong>JWT-based authentication</strong>,
and <strong>role-driven business rules</strong>
for a job portal domain.
</p>

<p><em>
Designed to explore how real-world backend systems prevent unauthorized actions
independent of controller-level checks.
</em></p>

<hr/>

</div>

<h2>💡 Problem This Project Addresses</h2>

<p>
In many backend applications, authorization logic is handled only at the controller layer,
making it vulnerable to bypass through alternative entry points.
</p>

<p>
This project intentionally enforces all authorization and business rules
inside the <strong>service layer</strong>,
ensuring invalid operations are blocked regardless of how the API is accessed.
</p>

<hr/>

<h2> Architecture Overview</h2>

<pre>
Client
  ↓
Controller (request/response only)
  ↓
Service (authorization + business rules)
  ↓
Repository
  ↓
PostgreSQL
</pre>

<p>
Controllers are intentionally kept thin.
All role checks, ownership validation, and state transitions occur in services.
</p>

<!-- Add architecture diagram image here -->

<hr/>

<h2>🔐 Roles & Authorization Rules</h2>

<h3>System Roles</h3>
<ul>
  <li><strong>EMPLOYER</strong></li>
  <li><strong>JOB_SEEKER</strong></li>
</ul>

<h3>Key Authorization Rules</h3>
<ul>
  <li>Only <strong>EMPLOYER</strong> users can create, update, or delete job postings</li>
  <li><strong>JOB_SEEKER</strong> users can apply to a job only once</li>
  <li>Employers cannot apply to jobs</li>
  <li>Invalid actions are rejected before any database mutation</li>
</ul>

<hr/>

<h2>📡 API Overview</h2>

<h3>Authentication</h3>
<pre>
POST /auth/register
POST /auth/login
</pre>

<h3>Employer Operations</h3>
<pre>
POST   /jobs
PUT    /jobs/{id}
DELETE /jobs/{id}
GET    /employer/jobs?page=0&size=10&sort=createdAt,desc
</pre>

<h3>Job Seeker Operations</h3>
<pre>
GET  /jobs?page=0&size=20&sort=createdAt,desc
POST /jobs/{id}/apply
GET  /applications/my
</pre>

<p>
Pagination, sorting, and JWT authentication are applied consistently across endpoints.
</p>

<hr/>

<h2>📝 Example Authorization Behavior</h2>

<h3>Unauthorized Access</h3>
<pre>
POST /jobs
Authorization: Bearer &lt;JOB_SEEKER_TOKEN&gt;

→ 403 FORBIDDEN
"Only employers can create jobs"
</pre>

<h3>Duplicate Application Prevention</h3>
<pre>
POST /jobs/42/apply
Authorization: Bearer &lt;JOB_SEEKER_TOKEN&gt;

→ 409 CONFLICT
"You have already applied to this job"
</pre>

<hr/>

<h2>🛡️ Security Design</h2>

<ul>
  <li>Stateless JWT authentication (no server-side sessions)</li>
  <li>Role and ownership checks enforced inside services</li>
  <li>Controllers do not contain authorization logic</li>
  <li>Security rules remain consistent across all API entry points</li>
</ul>

<hr/>

<h2>🗄️ Data Model</h2>

<ul>
  <li><strong>users</strong> — authentication and role management</li>
  <li><strong>jobs</strong> — job postings owned by employers</li>
  <li><strong>applications</strong> — job applications by seekers</li>
</ul>

<p>
A unique constraint on (job_id, seeker_id) prevents duplicate applications
at the database level.
</p>

<!-- Add database schema diagram here -->

<hr/>

<h2>🧪 Testing Focus</h2>

<ul>
  <li>Service-layer unit tests validating authorization rules</li>
  <li>Mockito used to isolate repositories</li>
  <li>Tests covering invalid role access and duplicate actions</li>
</ul>

<hr/>

<h2>⚙️ Running Locally</h2>

<pre>
git clone https://github.com/DeepsanBhandari/Role-BasedJobPortalSystem.git
cd Role-BasedJobPortalSystem
</pre>

<p>
Configure PostgreSQL and environment variables, then:
</p>

<pre>
mvn spring-boot:run
</pre>

<hr/>

<h2>🛠️ Tech Stack</h2>

<ul>
  <li>Java 17</li>
  <li>Spring Boot 3.2.x</li>
  <li>Spring Security 6.x</li>
  <li>PostgreSQL 15</li>
  <li>JWT (jjwt)</li>
  <li>Maven</li>
</ul>

<hr/>

<h2>🎯 What This Project Demonstrates</h2>

<ul>
  <li>Backend-first system design</li>
  <li>Service-layer enforcement of business rules</li>
  <li>Stateless authentication architecture</li>
  <li>Practical role-based access control</li>
  <li>Clean layered design suitable for testing and extension</li>
</ul>

<div align="center">
  <p><em>
  Built as a hands-on exploration of backend security and authorization patterns,
  not as a frontend-focused application.
  </em></p>
</div>
