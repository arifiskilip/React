import React, { useState } from 'react'
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import SignedIn from './SignedIn';

export default function Navi() {
 
  return (
    
  <div>
      <Navbar expand="lg" bg="dark" data-bs-theme="dark" className="bg-body-tertiary">
      <Container fluid>
        <Navbar.Brand href="#">Todo List</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
            navbarScroll
          >
          </Nav>
          <Form className="d-flex">
            {
              <SignedIn></SignedIn>
            }
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  </div>
  )
}
