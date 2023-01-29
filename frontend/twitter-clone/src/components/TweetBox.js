import React, {useState} from "react";
import TweetService from "../service/TweetService";
import {useSelector} from "react-redux";
import Box from "./Box";

const TweetBox = ({refresh}) => {
    const [content, setContent] = useState("");
    const currentUser = useSelector(state => state.reduxSlice.currentUser)

    const setContentFunc = x => setContent(x)

    const sendTweet = () => {
        let body = {
            text: content,
            userId: currentUser
        }
        let tweetService = new TweetService()
        tweetService.sendTweet(body).then(() => refresh())
    };
    return (
        <Box content={content}
             setContentFunc={setContentFunc}
             sendFunc={sendTweet}
             submit="Tweet"
             placeHolder="What's happening?"/>
    );
};

export default TweetBox;
