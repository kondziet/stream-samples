import { Editor } from "@monaco-editor/react";
import { useState } from "react";
import usePrivateClientRequest from "../hooks/usePrivateClientRequest";
import { streamCategoryData } from "../data/SidebarData";
import { useNavigate } from "react-router-dom";

function CreatePost() {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("Describe your stream sample...");
    const [postCategory, setPostCategory] = useState("");
    const [code, setCode] = useState("// create your first stream sample!");

    const privateClientRequest = usePrivateClientRequest();
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await privateClientRequest.post(
                "/api/posts/",
                {
                    title,
                    description,
                    postCategory,
                    code
                }
            );

            const postId = response.data;
            navigate(`/post-created/${postId}`);

        } catch (error) {
            console.log(error);
            return;
        }
        
    };

    const handleEditorChange = (value, event) => {
        setCode(value);
    };

    const renderedOptions = streamCategoryData.map(category => (<option key={category.id}>{category.value}</option>))

    return(
        <div className="w-screen flex-grow max-w-[1000px]">
            <form onSubmit={handleSubmit} className="flex flex-col gap-2 pt-4">
                <label>Title:</label>
                <input value={title} onChange={(event) => setTitle(event.target.value)}  className="w-full px-4 py-2 border border-gray-500 rounded-md focus:outline-none focus:border-green-300" type="text" required />
                <label>Description:</label>
                <textarea value={description} onChange={(event) => setDescription(event.target.value)} className="w-full h-32 px-4 py-2 border border-gray-500 rounded-md focus:outline-none focus:border-green-300" required></textarea>
                <label>Category:</label>
                <select value={postCategory} onChange={(event) => setPostCategory(event.target.value)} className="w-full px-4 py-2 border border-gray-500 rounded-md focus:outline-none focus:border-green-300" required>
                    <option value="" selected disabled>Select an option</option>
                    {renderedOptions}
                </select>
                <Editor
                    height="40vh"
                    language="java"
                    defaultValue={code}
                    theme="vs-dark"
                    onChange={handleEditorChange}
                />
                <button className="rounded-b-xl bg-green-400 px-4 py-2">Submit</button>
            </form>
            
            
        </div>
    );
}

export default CreatePost;