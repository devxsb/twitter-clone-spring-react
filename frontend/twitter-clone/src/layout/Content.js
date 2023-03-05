import React, {useEffect, useState} from "react";
import Divider from "../components/Divider";
import FeedList from "../components/FeedList";
import TweetBox from "../components/TweetBox";
import {PopulerIcon} from "../icons/Icon";
import TweetService from "../service/TweetService";
import profile from "../images/default-profile.png";
import {useSelector} from "react-redux";

const Content = () => {
    const [tweets, setTweets] = useState({content: []});
    const [render, setRender] = useState(false)

    const username = useSelector(state => state.reduxSlice.username)
    const profileImageLink = useSelector(state => state.reduxSlice.profileImageLink)

    useEffect(() => {
        let tweetService = new TweetService()
        tweetService.getTweets().then(res => setTweets(res.data))
    }, [render]);

    const refreshTweets = () => {
        setRender(!render)
    }

    return (
        <main className="flex flex-col border-r border-l border-gray-extraLight w-1/2 mr-auto">
            <header
                className="sticky top-0 z-10 bg-white flex justify-between items-center p-4 h-16 border-b border-gray-extraLight ">
                <span className="font-bold text-xl text-gray-900">Home</span>
                <PopulerIcon className="w-6 h-6 text-primary-base"/>
            </header>
            <div className="flex space-x-4 px-4 py-3">
                <img
                    src={profileImageLink ? `http://localhost:8080/v1/users/${username}/image/download` : profile}
                    alt="Profile"
                    className="w-11 h-11 rounded-full"
                />
                <TweetBox refresh={refreshTweets}/>
            </div>
            <Divider/>
            {/* Feed */}
            <FeedList tweets={tweets.content}/>
        </main>
    );
};

export default Content;
