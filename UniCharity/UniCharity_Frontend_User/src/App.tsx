import { RouterProvider } from 'react-router-dom';
import Router from './Router/Router';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useEffect } from 'react';

function App() {
  useEffect(() => {
    const currentPort = window.location.port;

    // Kiểm tra nếu cổng là 8080 và thay đổi sang 5073
    if (currentPort === "8080") {
      const newUrl = window.location.href.replace(":8080", ":5073");
      // Chuyển hướng lại trang với cổng mới
      window.location.href = newUrl;
    }
  }, []);

  return (
    <>
      <RouterProvider router={Router} />
      <ToastContainer />
    </>
  );
}

export default App;
