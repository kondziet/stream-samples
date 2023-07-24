import { Route, Routes } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Profile from "./pages/Profile";
import Posts from "./pages/Posts";
import Login from "./pages/Login";
import Logout from "./pages/Logout";
import CreatePost from "./pages/CreatePost";
import PostCreated from "./pages/PostCreated";
import Register from "./pages/Register";
import CategoryPost from "./pages/CategoryPosts";

function App() {

  return (
    <div className="flex flex-col items-center h-screen">
      <Sidebar />
      <Routes>
        <Route path="/" element={<Posts />} />
        <Route path="/categories/:category" element={<CategoryPost />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/create-post" element={<CreatePost />} />
        <Route path="/post-created/:id" element={<PostCreated />} />
      </Routes>
    </div>
  );
}

export default App;