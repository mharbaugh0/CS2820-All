WARP program for graph TestBug
Scheduler Name: Priority
M: 1.0
E2E: 1.0
nChannels: 16
Time Slot	A	B	C	D	E	F
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #1)	wait(#1)	sleep	sleep
1	sleep	sleep	sleep	if has(F1) push(F1: D -> E, #2)	wait(#2)	sleep
2	sleep	sleep	sleep	sleep	if has(F1) push(F1: E -> F, #1)	wait(#1)
3	sleep	sleep	sleep	sleep	sleep	sleep
4	sleep	sleep	sleep	sleep	sleep	sleep
5	sleep	sleep	sleep	sleep	sleep	sleep
6	sleep	sleep	sleep	sleep	sleep	sleep
7	sleep	sleep	sleep	sleep	sleep	sleep
8	sleep	sleep	sleep	sleep	sleep	sleep
9	sleep	sleep	sleep	sleep	sleep	sleep
10	sleep	sleep	sleep	sleep	sleep	sleep
11	sleep	sleep	sleep	sleep	sleep	sleep
// All flows meet their deadlines
