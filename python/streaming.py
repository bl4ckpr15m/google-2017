# -*- coding: utf-8 -*-
import math
from collections import Counter


class Streaming():

    def __init__(self, **kwargs):
        for key, value in kwargs.items():
            setattr(self, key, value)

    def update_cache_list(self):
        for v_pos in self.video_list:
            video = self.video_list[v_pos]
            for c_pos in range(0,len(self.cache_list)):
                cache = self.cache_list.get(c_pos)
                if(cache.mb - video >= 0):
                    cache.used = True
                    find = False
                    for vid_ca in cache.video_list:
                        if(vid_ca == video):
                            find = True
                    if(not find):
                        cache.mb -= video
                        cache.video_list.append(v_pos)
                        c_pos = len(self.cache_list)

    def print_cache_list(self):
        print len(self.cache_list)
        for pk, cache in self.cache_list.items():
            if(cache.used):
                print pk, " ".join(str(x) for x in cache.video_list)


class Cache():
    used = False
    video_list = []

    def __init__(self, **kwargs):
        for key, value in kwargs.items():
            setattr(self, key, value)


class Endpoint():

    def __init__(self, **kwargs):
        for key, value in kwargs.items():
            setattr(self, key, value)


class Request():

    def __init__(self, **kwargs):
        for key, value in kwargs.items():
            setattr(self, key, value)


def open_file():
    # f = open("me_at_the_zoo.in", "r")
    f = open("../in/example2.in", "r")
    line = f.readline()
    pos = 0

    v=ep=rq=ch=mb = 0
    cache_list = {}
    all_cache_list = {}
    endpoint_list = {}
    video_list = {}
    request_list = []

    while(line):
        if pos == 0:
            v,ep,rq,ch,mb = map(int, line.split(" "))
            line = f.readline()
        elif pos == 1:
            vid = map(int, line.split(" "))
            for i in range(v):
                video_list.update({i:vid[i]})

            line = f.readline()
        else:
            for i in range(ep):
                ms,num_cache = map(int, line.split(" "))
                for j in range(num_cache):
                    line = f.readline()
                    pk,time = map(int, line.split(" "))
                    cache = Cache(pk=pk, time=time, mb=mb)
                    cache_list.update({pk:cache})

                    if(all_cache_list.has_key(pk)):
                        all_cache_list.get(pk).video_list = list(set(
                                    all_cache_list.get(pk).video_list + cache_list.get(pk).video_list))
                    else:
                        all_cache_list.update({pk:Cache(pk=pk,mb=mb)})

                endpoint = Endpoint(time=ms, cache_list=cache_list)
                endpoint_list.update({i:endpoint})
                line = f.readline()
                cache_list = {}

            for i in range(rq):
                rq_video,rq_endpoint,numb = map(int, line.split(" "))
                req = Request(video=rq_video, endpoint=rq_endpoint, number=numb)
                request_list.append(req)
                line = f.readline()
        pos += 1

    return video_list, endpoint_list, request_list, all_cache_list

def print_data(endpoint_list, request_list, video_list):
    print "Endpoints:"
    for pk, endpoint in endpoint_list.items():
        print pk, endpoint.time
        print "\tCache used by endpoints:"
        for pk_c, cache in endpoint.cache_list.items():
            print "\t{} {} {}".format(pk_c, cache.time, cache.mb)

    print "Requests:"
    for request in request_list:
        print request.video, request.endpoint, request.number

    print "Videos:"
    for pk,video in video_list.items():
        print pk,video

    print "Solution:"

if __name__ == '__main__':
    video_list, endpoint_list, request_list, all_cache_list = open_file()
    print_data(endpoint_list, request_list, video_list)

    streaming = Streaming(video_list=video_list,
                        endpoint_list=endpoint_list,
                        request_list=request_list,
                        cache_list=all_cache_list)

    streaming.update_cache_list()
    # streaming.test()
    streaming.print_cache_list()
