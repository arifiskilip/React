import React from 'react'
import { Image, NavDropdown, Nav } from 'react-bootstrap'

export default function SignedIn() {
  return (
    <>
      <Nav className="ml-auto" style={{margin:"5px"}}>
      <Image src="https://png.pngtree.com/png-vector/20230903/ourmid/pngtree-man-avatar-isolated-png-image_9935818.png" roundedCircle width="30" height="30" className="mr-2" />
        <NavDropdown title="Arif İskilip" id="user-dropdown">
          Arif İskilip
          <NavDropdown.Divider />
          <NavDropdown.Item>Çıkış Yap</NavDropdown.Item>
          <NavDropdown.Item>Ayarlar</NavDropdown.Item>
        </NavDropdown>
      </Nav>
    </>
  )
}
