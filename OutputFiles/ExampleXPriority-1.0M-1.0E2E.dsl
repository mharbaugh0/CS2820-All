WARP program for graph ExampleX
Scheduler Name: Priority
M: 1.0
E2E: 1.0
nChannels: 16
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep
1	sleep	if has(F0) push(F0: B -> C, #2)	wait(#2)
2	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)
3	wait(#4)	if has(F1) push(F1: B -> A, #4)	sleep
4	sleep	sleep	sleep
5	if has(F0) push(F0: A -> B, #2)	wait(#2)	sleep
6	sleep	if has(F0) push(F0: B -> C, #3)	wait(#3)
7	sleep	sleep	sleep
8	sleep	sleep	sleep
9	sleep	sleep	sleep
// All flows meet their deadlines
