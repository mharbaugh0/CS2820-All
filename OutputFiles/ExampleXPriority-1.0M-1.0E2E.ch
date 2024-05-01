Channel Analysis for graph: ExampleXScheduler Name: Priority
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep	null	null	null	null	null	null	null
1	sleep	if has(F0) push(F0: B -> C, #2)	wait(#2)	null	null	null	null	null	null	null
2	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)	null	null	null	null	null	null	null
3	wait(#4)	if has(F1) push(F1: B -> A, #4)	sleep	null	null	null	null	null	null	null
4	sleep	sleep	sleep	null	null	null	null	null	null	null
5	if has(F0) push(F0: A -> B, #2)	wait(#2)	sleep	null	null	null	null	null	null	null
6	sleep	if has(F0) push(F0: B -> C, #3)	wait(#3)	null	null	null	null	null	null	null
7	sleep	sleep	sleep	null	null	null	null	null	null	null
8	sleep	sleep	sleep	null	null	null	null	null	null	null
9	sleep	sleep	sleep	null	null	null	null	null	null	null
10	null	null	null	null	null	null	null	null	null	null
11	null	null	null	null	null	null	null	null	null	null
12	null	null	null	null	null	null	null	null	null	null
13	null	null	null	null	null	null	null	null	null	null
14	null	null	null	null	null	null	null	null	null	null
15	null	null	null	null	null	null	null	null	null	null
