CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    project_name VARCHAR(255),
    description TEXT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50)
);

CREATE TABLE team_members (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(255),
    department VARCHAR(255)
);

CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    priority VARCHAR(50),
    status VARCHAR(50),
    due_date DATE,

    project_id BIGINT,
    member_id BIGINT,

    CONSTRAINT fk_project
        FOREIGN KEY(project_id)
        REFERENCES projects(id),

    CONSTRAINT fk_member
        FOREIGN KEY(member_id)
        REFERENCES team_members(id)
);