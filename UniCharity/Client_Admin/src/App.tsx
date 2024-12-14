import './App.css'
import {  RouterProvider } from 'react-router-dom'
import {  ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Router from './Router/Router';

function App() {
  // toast.success("success");
  return (
    <>
      <RouterProvider router={Router} />
      <ToastContainer />
    </>
  )
}

export default App
