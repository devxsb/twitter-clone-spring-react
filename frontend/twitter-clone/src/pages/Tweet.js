import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import TweetService from "../service/TweetService";
import FeedList from "../components/FeedList";
import CommentBox from "../components/CommentBox";
import {ArrowBack} from "@mui/icons-material";
import Divider from "../components/Divider";
import {useSelector} from "react-redux";

const Tweet = () => {
    const [tweet, setTweet] = useState()
    const [comments, setComments] = useState({content: []});

    const {id} = useParams();
    const navigate = useNavigate()
    const username = useSelector(state => state.reduxSlice.username)

    useEffect(() => {
        let tweetService = new TweetService()
        tweetService.getTweetById(id).then(res => setTweet(res.data))
        tweetService.getTweetsCommentByTweetId(id).then(res => setComments(res.data))
    }, []);
    return (
        <div className={"flex flex-col border-r border-l border-gray-extraLight w-1/2 mr-auto"}>
            <header
                className="sticky top-0 z-10 bg-white flex justify-between items-center p-4 border-b border-gray-extraLight ">
                <span className="font-medium text-lg text-gray-900">Replying to @{tweet && tweet.username}</span>
                <ArrowBack className="w-6 h-6 text-primary-base cursor-pointer" onClick={() => navigate("/")}/>
            </header>
            <div className="flex space-x-4 px-4 py-3">
                <img
                    src={`http://localhost:8080/v1/users/${username}/image/download`}
                    alt="Profile"
                    className="w-11 h-11 rounded-full"/>
                <CommentBox/>
            </div>
            <Divider/>
            <div className="flex flex-col border-r border-l border-gray-extraLight w-full mr-auto">
                {comments.content.length > 0 && <FeedList tweets={comments.content}/>}
            </div>
        </div>
    );
};

export default Tweet;