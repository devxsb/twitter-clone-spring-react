import axios from "axios";

export default class RetweetService {
    addRetweet(body) {
        return axios.post("/retweets", body)
    }
}