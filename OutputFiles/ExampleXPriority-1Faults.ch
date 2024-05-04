Channel Analysis for graph: ExampleXScheduler Name: Priority
Time Slot	A	B	C	D	E	F	G	H	I	J	K	L	M	N	O	P
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep	null	null	null	null	null	null	null
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)	null	null	null	null	null	null	null
2	sleep	if has(F0) push(F0: B -> C, #3)	wait(#3)	null	null	null	null	null	null	null
3	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)	null	null	null	null	null	null	null
4	wait(#6)	if has(F1) push(F1: B -> A, #6) else pull(F1: C -> B, #6)	wait(#6)	null	null	null	null	null	null	null
5	if has(F0) push(F0: A -> B, #3)	wait(#3)	sleep	null	null	null	null	null	null	null
6	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)	null	null	null	null	null	null	null
7	sleep	if has(F0) push(F0: B -> C, #5)	wait(#5)	null	null	null	null	null	null	null
8	wait(#7)	if has(F1) push(F1: B -> A, #7)	sleep	null	null	null	null	null	null	null
9	sleep	sleep	sleep	null	null	null	null	null	null	null
10	null	null	null	null	null	null	null	null	null	null
11	null	null	null	null	null	null	null	null	null	null
12	null	null	null	null	null	null	null	null	null	null
13	null	null	null	null	null	null	null	null	null	null
14	null	null	null	null	null	null	null	null	null	null
15	null	null	null	null	null	null	null	null	null	null
// All flows meet their deadlines
