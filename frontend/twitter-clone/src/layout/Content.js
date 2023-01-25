import React, {useEffect, useState} from "react";
import Divider from "../components/Divider";
import FeedList from "../components/FeedList";
import TweetBox from "../components/TweetBox";
import {PopulerIcon} from "../icons/Icon";

const Content = () => {
    const [tweets, setTweets] = useState([]);

    useEffect(() => {
        // simulation
    }, []);

    return (
        <main className="flex flex-col border-r border-l border-gray-extraLight w-1/2 mr-auto">
            <header
                className="sticky top-0 z-10 bg-white flex justify-between items-center p-4 border-b border-gray-extraLight ">
                <span className="font-bold text-xl text-gray-900">Home</span>
                <PopulerIcon className="w-6 h-6 text-primary-base"/>
            </header>
            <div className="flex space-x-4 px-4 py-3">
                <img
                    src="https://pbs.twimg.com/profile_images/1617244452027879425/cODTtPoH_400x400.jpg"
                    alt="Profile"
                    className="w-11 h-11 rounded-full"
                />
                <TweetBox/>
            </div>
            <Divider/>

            {/* Feed */}
            <FeedList tweets={tweets}/>
        </main>
    );
};

export default Content;
