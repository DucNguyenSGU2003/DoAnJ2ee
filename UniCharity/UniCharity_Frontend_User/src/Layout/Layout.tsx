import React from 'react'
import Topbar from '../Component/Topbar/Topbar'
import Navbar from '../Component/Navbar/Navbar'
import { Outlet } from 'react-router-dom'
import { UserProvider } from '../Context/UseAuth'

const Layout = () => {
  return (
    <UserProvider>
      <Topbar />
      <Navbar />
      <main>
        <Outlet />
      </main> 
    </UserProvider>
  )
}

export default Layout
