import axios from 'axios'

export default class TweetService {
    getTweets() {
        return axios.get('/tweets')
    }

    sendTweet(body) {
        return axios.post("/tweets", body)
    }
}