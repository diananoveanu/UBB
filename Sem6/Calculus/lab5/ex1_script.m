Time = [0 3 5 8 13];
Distance = [0 225 383 623 993];
Speed = [75 77 80 74 72];

[D, S] = Hermite(Time, [Distance; Speed], [10]); %symbolic Hermite polynomial


printf("Distance at time T=10 is %f, speed at time T = 10 is %f \n", D, S);



