Channel Analysis for graph: TestBugScheduler Name: Priority
Time Slot	A	B	C	D	E	F	G	H	I	J	K	L	M	N	O	P
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #1)	wait(#1)	sleep	sleep	null	null	null	null	null	null
1	sleep	sleep	sleep	if has(F1) push(F1: D -> E, #2)	wait(#2)	sleep	null	null	null	null	null	null
2	sleep	sleep	sleep	sleep	if has(F1) push(F1: E -> F, #1)	wait(#1)	null	null	null	null	null	null
3	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
4	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
5	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
6	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
7	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
8	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
9	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
10	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
11	sleep	sleep	sleep	sleep	sleep	sleep	null	null	null	null	null	null
12	null	null	null	null	null	null	null	null	null	null	null	null
13	null	null	null	null	null	null	null	null	null	null	null	null
14	null	null	null	null	null	null	null	null	null	null	null	null
15	null	null	null	null	null	null	null	null	null	null	null	null
// All flows meet their deadlines
