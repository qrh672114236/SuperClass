//
// Created by Administrator on 2019/8/14 0014.
//

#ifndef SUPERCLASS_BASECHANNEL_H
#define SUPERCLASS_BASECHANNEL_H

class BaseChannel{
public:
    BaseChannel(int id):id(id){}
    virtual ~BaseChannel(){}
    int id;
};

#endif //SUPERCLASS_BASECHANNEL_H
