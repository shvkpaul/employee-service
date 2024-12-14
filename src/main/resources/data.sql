CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    employee_id BIGINT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- Create a stored procedure
CREATE ALIAS DeleteRoleAndReassignProjects FOR "com.shvkpaul.employee.service.StoredProcedures.deleteRoleAndReassignProjects";

-- Insert roles
INSERT INTO role (name) VALUES ('Developer');
INSERT INTO role (name) VALUES ('Manager');
INSERT INTO role (name) VALUES ('Tester');
INSERT INTO role (name) VALUES ('Architect');
INSERT INTO role (name) VALUES ('Designer');

-- Insert employees
INSERT INTO employee (firstname, surname, role_id) VALUES ('S', 'Paul', 1);
INSERT INTO employee (firstname, surname, role_id) VALUES ('M', 'Das', 2);
INSERT INTO employee (firstname, surname, role_id) VALUES ('P', 'Saha', 1);
INSERT INTO employee (firstname, surname, role_id) VALUES ('Q', 'Sen', 1);

-- Insert projects
INSERT INTO project (name, employee_id) VALUES ('Project A', 1);
INSERT INTO project (name, employee_id) VALUES ('Project B', 2);
