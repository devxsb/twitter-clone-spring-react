import axios from 'axios'

export default class TweetService {

    getTweets() {
        return axios.get('/tweets')
    }

    getTweetById(id) {
        return axios.get("/tweets/" + id);
    }

    sendTweet(body) {
        return axios.post("/tweets", body)
    }

    getTweetsCommentByTweetId(id) {
        return axios.get((`/tweets/${id}/comments`))
    }
}