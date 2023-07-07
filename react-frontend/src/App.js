import { Route, RouterProvider, Routes, createBrowserRouter } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import ErrorPage from "./pages/ErrorPage";
import Profile from "./pages/Profile";
import Posts from "./pages/Posts";
import Login from "./pages/Login";

function App() {

  return (
    <div className="App">
      <Sidebar />
      <Routes>
        <Route path="/" element={<Posts />} />
        <Route path="/categories" element={<Posts />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </div>
  );
}

export default App;