import { useEffect, useState } from "react";
import clientRequest from "../api/clientRequest";
import Post from "../components/Post";
import usePrivateClientRequest from "../hooks/usePrivateClientRequest";

function Posts() {
    const [posts, setPosts] = useState([]);
    const privateClientRequest = usePrivateClientRequest();

    useEffect(() => {
        let isMounted = true;
        const abortController = new AbortController();

        const cleanup = () => {
            isMounted = false;
            abortController.abort();
        };

        const getPosts = async () => {
            try {
                const response = await privateClientRequest.get("/api/posts", {
                    signal: abortController.signal
                });
                if (isMounted) {
                    setPosts(response.data);
                }
            } catch (error) {
                console.error(error);
            }
        };

        getPosts();

        return cleanup;
    }, []);

    const renderedPosts = posts.map(post => {
        return(
            <Post post={post} />
        );
    });

    return(
        <div>
            <h1>Posts</h1>
            {renderedPosts}
        </div>
    );
}

export default Posts;