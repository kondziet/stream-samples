import { useEffect, useState } from "react";
import clientRequest from "../api/clientRequest";
import Post from "../components/Post";

function Posts() {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        let isMounted = true;
        const abortController = new AbortController();

        const cleanup = () => {
            isMounted = false;
            abortController.abort();
        };

        const getPosts = async () => {
            try {
                const response = await clientRequest.get("/api/posts/home", {
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

    const renderedPosts = posts.map((post, index) => {
        return(
            <Post key={index} post={post} />
        );
    });

    return(
        <div className="flex flex-col items-center w-screen flex-grow max-w-[1240px]">
            {renderedPosts}
        </div>
    );
}

export default Posts;